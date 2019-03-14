<template>
  <drag
    :transferData='data'
    @dragstart='dragstart'
    @dragend='dragend'
    class='puzz-component'
    :style='{ "background-color": getColorCode(data.color) }'
  >
    <span> {{ data.name }} </span>
  </drag>
</template>

<script>
import config from '@/config'
import { Drag, Drop } from 'vue-drag-drop'

export default {
  name: 'puzz-component',
  props: {
    data: {
      type: Object,
      required: true
    }
  },
  computed: {
    isWrapper () {
      return this.data.type !== 'expression' && this.isShowInput
    }
  },
  methods: {
    dragstart (ev) {
      this.$store.commit('question/DRAG_START', {
        dragging: this.data,
        draggingElement: this.$el
      })
    },
    dragend (ev) {
      this.$store.commit('question/DRAG_END')
    },
    getColorCode (name) {
      return config.colorCodes[name]
    }
  },
  components: {
    Drag,
    Drop
  }
}
</script>

<style scoped lang='sass'>
.puzz-component
  height: 30px
  width: 50%
  margin: 5px auto
  color: #fff
  vertical-align: middle
  padding: 5px
</style>
