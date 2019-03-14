<template lang='pug'>
  v-container.question(fluid)
    v-toolbar(dark)
      v-toolbar-title Question: {{ question.title }}
      v-spacer
      v-btn(color='primary' @click='runCode' outline)
        v-icon play_arrow
        span run
      v-btn(color='warning' @click='clearComponents' outline)
        v-icon block
        span clear

    puzz-dialog(
      :text='dialog.text'
      :isCorrect='dialog.isCorrect'
    )

    v-layout.main(row wrap fill-height)
      v-flex.panes(xs4)
        p {{ question.content }}

      v-flex#playGround.panes(xs4)
        drop.drop-zone(
          :class='{ over }'
          @drop.self='onDropComponent'
          @dragover='over = true'
          @dragleave='over = false'
        )
          div(v-for='(val, i) in playGround' :key='"key-" + i')
            puzz-wrapper.component(
              v-if='val.type === "wrapper"'
              :data='val'
            )
            puzz-expression.component(
              v-if='val.type === "expression"'
              :data='val'
            )

      v-flex#components.panes(xs4)
        drop.drop-zone(@drop.self='onDropToDelete')
          div(v-for='(val, i) in components' :key='"key-" + i')
            puzz-component.component(:data='val')

    v-footer(absolute height='20%')
      v-textarea(box label='Result' readonly v-model='result' :hint='latest')
    notifications(group='notify' position='bottom right')
</template>

<script>
import { mapState } from 'vuex'
import { Drop } from 'vue-drag-drop'
import moment from 'moment'
import PuzzComponent from '@/components/playground/PuzzComponent.vue'
import PuzzWrapper from '@/components/playground/PuzzWrapper.vue'
import PuzzExpression from '@/components/playground/PuzzExpression.vue'
import PuzzDialog from '@/components/playground/PuzzDialog.vue'
import config from '@/config'

export default {
  name: 'question',
  mounted: function () {
    this.$store.dispatch('questions/getOne', { id: this.$route.params.id })

    this.components = this.components.map(e => ({
      ...e,
      color: config.colors[e.name]
    }))
  },
  computed: {
    ...mapState({
      question: state => state.questions.selecting
    }),
    contents: () => document.querySelector('#playGround').textContent
  },
  data () {
    return {
      components: [
        { name: 'variable', type: 'expression' },
        { name: 'if', type: 'wrapper' },
        { name: 'elsif', type: 'wrapper' },
        { name: 'else', type: 'wrapper' },
        { name: 'for', type: 'wrapper' },
        { name: 'while', type: 'wrapper' },
        { name: 'puts', type: 'expression' }
      ],
      playGround: [],
      over: false,
      result: '',
      latest: '',
      dialog: {
        text: '',
        isCorrect: false
      }
    }
  },
  methods: {
    onDropComponent (data, ev) {
      const target = this.$store.state.question.draggingElement
      if (!target.classList.contains('puzz-component')) {
        target.parentNode.removeChild(target)
      }

      this.playGround.push(data)
    },
    clearComponents (ev) {
      this.playGround = []
      this.result = ''
      this.latest = ''
    },
    onDropToDelete (ev) {
      const target = this.$store.state.question.draggingElement
      if (!target.classList.contains('puzz-component')) {
        target.parentNode.removeChild(target)
      }
    },
    async runCode (ev) {
      if (!this.playGround.length) {
        alert('コンポーネントを配置してください')
        return
      }

      let code = document.querySelector('#playGround').textContent
      code = code.replace(/\svariable/g, ' ')
      code = code.replace(/\sputs/g, ' puts ')

      const dialogData = await this.$store.dispatch('question/sendCode', {
        id: this.$route.params.id,
        code
      })

      this.handleResult(dialogData)
    },
    handleResult (data) {
      if (data.result) {
        this.dialog.text = '正解！'

        this.$store.commit('question/OPEN_DIALOG')

        if (data.example) {
          this.dialog.isCorrect = true
          this.dialog.text = '正解！ おめでとう！'
        } else {
          this.dialog.isCorrect = false
          this.dialog.text = '正解！'
        }
      } else {
        let title = 'ミス'
        let text = data.stderr

        if (data.stderr) {
          this.$notify({
            group: 'notify',
            title,
            text,
            type: 'error'
          })
        }
      }

      this.result = data.stdout
      this.latest = 'last modified @' + moment().format('YYYY-MM-DD HH:mm:ss')
    }
  },
  components: {
    Drop,
    PuzzComponent,
    PuzzWrapper,
    PuzzExpression,
    PuzzDialog
  }
}
</script>

<style lang='sass'>
.question
  padding: 0
  height: 90%
  .main
    font-size: 1.2em
    .panes
      overflow: scroll
      height: 70vh
      &:nth-child(odd)
        background-color: #f5f5f5
      p
        margin: 10px
      .drop-zone
        width: 100%
        height: 100%
      .component
        border-radius: 5px
        padding: auto 15px
        cursor: grab
        &:active
          cursor: grabbing
        input
          text-align: center
        .none
          display: none
  #playGround
    .component
      color: #fff
      margin: 10px 5px
      padding: 10px
</style>
