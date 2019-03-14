<template lang='pug'>
  v-layout(row)
    v-flex(xs3)
      span {{ data.name }}
    v-flex(xs9)
      v-text-field(dark placeholder='何か' type='text' v-model='inputValue')
      span.none {{ inputValue }};
</template>

<script>
export default {
  name: 'puzz-puts',
  props: {
    data: {
      required: true
    }
  },
  data () {
    return {
      inputValue: ''
    }
  }
}
</script>

<style>
</style>
