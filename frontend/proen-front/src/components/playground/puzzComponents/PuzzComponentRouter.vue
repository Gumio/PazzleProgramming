<script>
import PuzzExpressionRouter from '@/components/playground/puzzComponents/abstracts/PuzzExpressionRouter.vue'
import PuzzWrapperRouter from '@/components/playground/puzzComponents/abstracts/PuzzWrapperRouter.vue'
import config from '@/config'

export default {
  name: 'puzzComponentRouter',
  props: {
    data: {
      required: true,
      validator (val) {
        const keys = Object.keys(val)
        return !['name', 'symbol'].map(e => keys.includes(e)).includes(false)
      }
    }
  },
  render (h) {
    const symbol = this.data.symbol
    const isExpression = Object.keys(config.components.types.expression).includes(symbol)

    const dataWithComponentColor = {
      ...this.data,
      color: config.colors[this.data.name],
      inputs: {
        name: '',
        value: ''
      }
    }
    const style = { 'background-color': this.getColorCode(dataWithComponentColor.color) }

    return h(isExpression ? 'puzz-expression-router' : 'puzz-wrapper-router', {
      props: { data: dataWithComponentColor },
      style
    })
  },
  methods: {
    getColorCode (name) {
      return config.colorCodes[name]
    }
  },
  components: {
    PuzzExpressionRouter,
    PuzzWrapperRouter
  }
}
</script>

<style lang='sass'>
.component
  border-radius: 5px
  cursor: grab
  margin: 10px 5px
  padding: 10px
  color: #fff
  &:active
    cursor: grabbing
  input
    text-align: center
  .none
    display: none
</style>
