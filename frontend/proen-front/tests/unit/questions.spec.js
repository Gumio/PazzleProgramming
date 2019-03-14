import { shallowMount } from '@vue/test-utils'
import HelloWorld from '@/views/Questions.vue'

describe('Questions.vue', () => {
  it('', () => {
    const msg = 'new message'
    const wrapper = shallowMount(HelloWorld, {
      propsData: { msg }
    })
    expect(wrapper.text()).toMatch(msg)
  })
})
