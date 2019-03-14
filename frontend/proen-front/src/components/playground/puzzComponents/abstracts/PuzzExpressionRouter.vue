<script>
import config from '@/config'
import AbstractPuzzBase from '@/components/playground/puzzComponents/abstracts/AbstractPuzzBase'
import PuzzVariable from '@/components/playground/puzzComponents/components/expressions/PuzzVariable.vue'
import PuzzPuts from '@/components/playground/puzzComponents/components/expressions/PuzzPuts.vue'

export default {
  name: 'puzz-expression-router',
  mixins: [AbstractPuzzBase],
  render (h) {
    return (
      <drag
        class='expression component'
        transferData={ this.data }
        onDragstart={ this.dragStart }
      >
        { this.getComponent(this.data.symbol) }
      </drag>
    )
  },
  props: {
    data: {
      required: true
    }
  },
  methods: {
    getComponent (name) {
      const { VARIABLE, PUTS } = config.components.types.expression
      switch (name) {
        case VARIABLE:
          return (<PuzzVariable data={ this.data } />)
        case PUTS:
          return (<PuzzPuts data={ this.data } />)
      }
    }
  },
  components: {
    PuzzVariable,
    PuzzPuts
  }
}
</script>

<style lang='sass' scoped>
.expression
  min-height: 25px
</style>
