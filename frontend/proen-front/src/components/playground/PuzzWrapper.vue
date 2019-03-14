<template>
  <drag
    class='wrapper'
    :style='{ "background-color": getColorCode(data.color) }'
    :transferData='data'
    @dragstart='dragStart'
  >
    <v-layout>
      <v-flex xs2>
        <span class='font-weight-medium'>{{ data.name }}</span>
      </v-flex>
      <v-flex xs4
        v-if='!isElse(data.name)'
      >
        <v-text-field
          dark type='text' v-model='inputName'
          placeholder='何か'
        />
        <span class='none'> {{ inputName }} </span>
      </v-flex>
      <v-flex xs2
        v-if='!isElse(data.name)'
      >
        <puzz-selector :items='operators[data.name]'/>
      </v-flex>
      <v-flex xs4
        v-if='!isElse(data.name)'
      >
        <v-text-field
          dark type='text' v-model='inputValue'
          placeholder='何か'
        />
        <span class='none'> {{ inputValue }} ; </span>
      </v-flex>
    </v-layout>

    <drop class='wrapper-inner' @drop='onDropInner'>
      <div v-for='(v, i) in children' :key='"key" + i'>
        <puzz-expression
          :data='v' v-if='v.type === "expression"'
          class='component'
        />
        <puzz-wrapper
          :data='v' v-if='v.type === "wrapper"'
          class='component'
        />
      </div>
    </drop>
    <span class='none' v-if='shouldShowEnd(data.name)'> end; </span>
  </drag>
</template>

<script>
import { mapState } from 'vuex'
import { Drag, Drop } from 'vue-drag-drop'
import PuzzExpression from '@/components/playground/PuzzExpression.vue'
import PuzzWrapper from '@/components/playground/PuzzWrapper.vue'
import PuzzSelector from '@/components/playground/PuzzSelector.vue'
import config from '@/config'

const operators = config.components.operators.wrappers

export default {
  name: 'puzzWrapper',
  props: {
    data: {
      required: true
    }
  },
  data () {
    return {
      inputName: '',
      inputValue: '',
      children: [],
      operators: operators
    }
  },
  computed: {
    ...mapState({
      draggingElement: state => state.question.draggingElement
    })
  },
  methods: {
    onDropInner (data, ev) {
      ev.stopPropagation()
      const target = this.draggingElement
      if (!target.classList.contains('puzz-component')) { target.parentNode.removeChild(target) }

      this.children.push(data)
    },
    shouldShowEnd (name) {
      return !['elsif', 'else'].includes(name)
    },
    isElse (name) {
      return name === 'else'
    },
    dragStart (data, ev) {
      ev.stopPropagation()

      this.$store.commit('question/DRAG_START', {
        dragging: data,
        draggingElement: this.$el
      })
    },
    getColorCode (name) {
      return config.colorCodes[name]
    }
  },
  components: {
    Drag,
    Drop,
    PuzzExpression,
    PuzzWrapper,
    PuzzSelector
  }
}
</script>

<style lang='sass' scoped>
.wrapper
  min-height: 50px
  &-inner
    padding-bottom: 20px
    min-height: 100px
    width: 100%
    margin-left: 10px
</style>
