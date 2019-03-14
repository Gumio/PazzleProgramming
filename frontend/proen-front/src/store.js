import Vue from 'vue'
import Vuex from 'vuex'
import common from '@/vuex/index'
import questions from '@/vuex/questions'
import question from '@/vuex/question'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    common,
    questions,
    question
  }
})
