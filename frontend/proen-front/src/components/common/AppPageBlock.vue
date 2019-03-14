<template>
  <div class='block' :class='{ pad: !full }'>
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'app-page-block',
  props: {
    full: Boolean
  },
  mounted: function () {
  }
}
</script>

<style lang='sass' scoped>
.block
  height: 600px
  width: 100%
  img
    height: 100%
    border-radius: 10px
.pad
  padding: 10px
</style>
