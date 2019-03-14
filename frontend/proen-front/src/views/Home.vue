<template lang='pug'>
  v-container(fluid text-xs-center)
    app-page-block
      p プログラミングをパズルで解くゲームです
    app-page-block(full)
      p プログラミングをパズルで解くゲームです
    app-page-block(full)
      h1 せつめー
</template>

<script>
import AppPageBlock from '@/components/common/AppPageBlock.vue'

export default {
  name: 'home',
  mounted: function () {},
  data () {
    return {}
  },
  components: {
    AppPageBlock
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang='sass' scoped>
h1
  font-size: 2em
p
  font-size: 1.5em
</style>
