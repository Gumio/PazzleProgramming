<template lang='pug'>
  v-layout
    v-flex(xs2)
      span.font-weight-medium {{ data.name }}
    v-flex(xs4)
      v-text-field(
        dark type='text' v-model='data.inputs.name'
        placeholder='何か'
      )
      span.none {{ data.inputs.name }}
    v-flex(xs2)
      puzz-selector(:items='operators[data.name]')
    v-flex(xs4)
      v-text-field(
        dark type='text' v-model='data.inputs.value'
        placeholder='何か'
      )
      span.none {{ data.inputs.value }} ;
</template>

<script>
import PuzzSelector from '@/components/playground/PuzzSelector.vue'
import config from '@/config'

const operators = config.components.operators.wrappers

export default {
  name: 'puzzFor',
  props: {
    data: {
      required: true
    }
  },
  data () {
    return {
      operators
    }
  },
  components: {
    PuzzSelector
  }
}
</script>

<style>

</style>
