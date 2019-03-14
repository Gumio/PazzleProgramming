import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Questions from './views/Questions.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  },
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ '@/views/About.vue')
    },
    {
      path: '/questions',
      name: 'questions',
      component: Questions
    },
    {
      path: '/questions/:id',
      name: 'question',
      component: () => import(/* webpackChunkName: "about" */ '@/views/Question.vue')
    }
  ]
})
