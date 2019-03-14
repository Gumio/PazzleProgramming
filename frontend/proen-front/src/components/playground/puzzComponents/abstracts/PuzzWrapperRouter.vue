<script>
import { Drag, Drop } from 'vue-drag-drop'
import Draggable from 'vuedraggable'
import config from '@/config'
import PuzzIf from '@/components/playground/puzzComponents/components/wrappers/PuzzIf.vue'
import PuzzWhile from '@/components/playground/puzzComponents/components/wrappers/PuzzWhile.vue'
import PuzzFor from '@/components/playground/puzzComponents/components/wrappers/PuzzFor.vue'
import PuzzComponentRouter from '@/components/playground/puzzComponents/PuzzComponentRouter.vue'

const operators = config.components.operators.wrappers

export default {
  name: 'puzz-wrapper-router',
  render (h) {
    const puzzComponentRouters = this.children.map((e, i) => {
      const comp = <PuzzComponentRouter data={ e }></PuzzComponentRouter>
      return comp
    })

    return (
      <Drag
        name='dtag-components'
        class='wrapper component'
        transferData={ this.data }
        dragstart={ this.dragStart }
      >
        <v-layout row>
          { this.getComponent(this.data.symbol) }
        </v-layout>

        <Drop class='wrapper-inner' onDrop={ this.onDropInner }>
          <Draggable value={ this.children } options={ { group: 'children' } }
            onChoose={ this.onChoose }
            onEnd={ this.handle }
          >
            { puzzComponentRouters }
          </Draggable>
        </Drop>
        <span class='none'> end;</span>
      </Drag>
    )
  },
  props: {
    data: {
      required: true
    }
  },
  data () {
    return {
      inputs: {
        name: '',
        value: ''
      },
      children: [],
      operators,
      sorting: false
    }
  },
  methods: {
    getComponent (name) {
      const { IF, WHILE, FOR } = config.components.types.wrapper

      switch (name) {
        case IF:
          return (<PuzzIf data={ this.data } />)
        case WHILE:
          return (<PuzzWhile data={ this.data } />)
        case FOR:
          return (<PuzzFor data={ this.data } />)
      }
    },
    onDropInner (data, ev) {
      ev.stopPropagation()
      // const target = this.draggingElement
      // if (!target.classList.contains('puzz-component')) { target.parentNode.removeChild(target) }
      console.log(this.sorting)
      if (!this.sorting) {
        this.children.push(data)
      }
    },
    isWrapper (symbol) {
      const types = config.components.types
      const isWrapper = Object.keys(types.wrapper).includes(symbol)
      return isWrapper
    },
    dragStart (data, e) {
      // console.log(data)
      // console.log('hoge')
    },
    onChoose (e) {
      this.sorting = true
      console.log('sorting: ', this.sorting)
    },
    handle (e) {
      console.log('handle: ', this.sorting)
    }
  },
  components: {
    PuzzIf,
    PuzzWhile,
    PuzzFor
  }
}
</script>

<style lang='sass'>
.wrapper
  min-height: 50px
  &-inner
    padding-bottom: 20px
    min-height: 100px
    width: 100%
    margin-left: 10px
</style>
