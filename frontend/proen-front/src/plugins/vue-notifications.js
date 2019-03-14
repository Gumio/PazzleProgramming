import Vue from 'vue'
import VueNotification from 'vue-notification'

Vue.use(VueNotification)
Vue.notify({
  group: 'notify',
  title: 'title',
  text: 'test',
  position: 'bottom right',
  duration: 2000
})
