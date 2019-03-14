<template>
  <v-select :items='items' :value='items[0]'></v-select>
</template>

<script>
export default {
  name: 'puzz-selector',
  props: {
    items: {
      required: true
    }
  }
}
</script>

<style lang='sass' scoped>

</style>
