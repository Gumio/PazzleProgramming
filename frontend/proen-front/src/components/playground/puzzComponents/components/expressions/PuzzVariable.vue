<template lang='pug'>
  v-layout(row)
    v-flex(xs2)
      span.font-weight-medium {{ data.name }}
    v-flex(xs4)
      v-text-field.input(dark placeholder='変数名' type='text' v-model='inputName')
      span.none {{ inputName }}
    v-flex(xs2)
      puzz-selector(:items='operators[data.name]')
    v-flex(xs4)
      v-text-field(dark placeholder='値' type='text' v-model='inputValue')
      span.none {{ inputValue }};
</template>

<script>
import config from '@/config'
import PuzzSelector from '@/components/playground/PuzzSelector.vue'

const operators = config.components.operators.expressions

export default {
  name: 'puzz-variable',
  props: {
    data: {
      required: true
    }
  },
  data () {
    return {
      inputName: '',
      inputValue: '',
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
