<template lang='pug'>
  v-container(fluid)
    h1 questions
    v-toolbar(
      dense
      floating
      flat
    )
      v-text-field(
        hide-details
        prepend-icon="search"
        single-line
        v-model='search'
      )
    v-layout(row wrap)
      v-flex(xs6 v-for='(d, i) in filteredQuestions' :key='"key" + i')
        v-hover
          v-card.card.white--text(
            slot-scope='{ hover }'
            color='blue-grey darken-2'
          )
            v-card-title(primary-title)
              h3 {{ d.title }}
            v-card-text(style='height: 100px')
              div {{ shortText(d.content) }}
            v-expand-transition
              router-link.link.d-flex.transition-fast-in-fast-out.orange.darken-2.card-reveal.white--text(
                v-if="hover"
                style="height: 100%;"
                :to='"/questions/" + d.id'
                tag='div'
              ) {{ d.content }}
            v-divider
            v-card-actions.tags
              v-chip.component(
                v-for='c in d.components' :key='"key" + c'
                :color='getComponentColor(c)'
              ) {{ c }}
        //-  InfiniteLoading(@infinite='infiniteHandler')
</template>

<script>
import { mapState } from 'vuex'
import InfiniteLoading from 'vue-infinite-loading'
import config from '@/config'

export default {
  name: 'home',
  mounted: function () {
    this.$store.dispatch('questions/getSome')
  },
  computed: {
    ...mapState({
      questions: state => state.questions.all
    }),
    filteredQuestions () {
      return this.questions.filter(e => e.title.includes(this.search))
    }
  },
  data () {
    return {
      colors: config.colors,
      search: ''
    }
  },
  methods: {
    shortText (text) {
      return text.substring(0, 25)
    },
    getComponentColor (name) {
      return this.colors[name]
    },
    async infiniteHandler ($state) {
      const response = await this.$store.dispatch('questions/getSome')
      if (!response.length) {
        $state.complete()
      } else {
        $state.loaded()
      }
    }
  },
  components: {
    InfiniteLoading
  }
}
</script>

<style scoped lang='sass'>
.card
  margin: 5%
  &-reveal
    align-items: center
    bottom: 0
    justify-content: center
    opacity: .9
    position: absolute
    width: 100%
    padding: 10px
    overflow: scroll
  .tags
    overflow: scroll
    .component
      margin: 0 5px
</style>
