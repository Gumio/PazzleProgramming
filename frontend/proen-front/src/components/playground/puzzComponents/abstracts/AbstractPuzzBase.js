import { Drag, Drop } from 'vue-drag-drop'
import { DRAG_START } from '../../../../vuex/question'

export default {
  methods: {
    dragStart (data, ev) {
      ev.stopPropagation()

      this.$store.commit(DRAG_START, {
        dragging: data,
        draggingElement: this.$el
      })
    }
  },
  components: {
    Drag,
    Drop
  }
}
