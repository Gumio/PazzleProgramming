import config from '@/config'
const BASE = 'question'

export const DRAG_START = `${BASE}/DRAG_START`
export const DRAG_END = `${BASE}/DRAG_END`
export const DRAG_DIALOG = `${BASE}/DRAG_DIALOG`

export const RESET = `${BASE}/RESET_COMPONENT`
export const ADD = `${BASE}/ADD_COMPONENT`
export const INSERT = `${BASE}/INSERT_COMPONENT`

export const ADD_CHILD = `${BASE}/INSERT_COMPONENT`
export const INSERT_CHILD = `${BASE}/INSERT_COMPONENT`

export default {
  namespaced: true,
  state: {
    dragging: '',
    draggingElement: '',
    dialog: false,
    key: 0,
    componentsData: []
  },
  mutations: {
    DRAG_START (state, payload) {
      state.dragging = payload.dragging
      state.draggingElement = payload.draggingElement
    },
    DRAG_END (state) {
      state.dragging = ''
      state.draggingElement = ''
    },
    OPEN_DIALOG (state) {
      state.dialog = true
    },
    RESET (state) {
      state.key = 0
      state.componentsData = []
    },
    ADD (state, payload) {
      const tmp = { ...payload, name: 'name', key: ++state.key }
      state.componentsData.push(tmp)
      console.log(state.componentsData)
    },
    INSERT (state, { index, data }) {
      const tmp = { ...data, key: ++state.key }
      state.componentsData[index].push(tmp)
    },
    INSERT_CHILD (state, { index, data }) {

    }
  },
  actions: {
    async sendCode ({ commit }, { id, code }) {
      const params = new URLSearchParams()
      params.append('id', id)
      params.append('code_text', code)
      const response = await this._vm.axios.post(`${config.api.base_url}/execute`, params)
        .catch(() => {
          return {
            data: {
              result: false,
              stderr: '意図しないエラーが発生しました',
              stdout: ''
            }
          }
        })

      return response.data
    }
  }
}
