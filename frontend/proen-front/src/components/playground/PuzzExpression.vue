<template>
  <drag
    class='expression'
    :style='{ "background-color": getColorCode(data.color) }'
    :transferData="data"
    @dragstart='dragStart'
  >
    <v-layout row v-if='data.name === "variable"'>
      <v-flex xs2>
        <span class='font-weight-medium'> {{ data.name }}</span>
      </v-flex>
      <v-flex xs4>
        <v-text-field dark placeholder='変数名' type='text' class='input' v-model='inputName' />
        <span class='none'>{{ inputName }} </span>
      </v-flex>
      <v-flex xs2>
        <puzz-selector :items='operators[data.name]'/>
      </v-flex>
      <v-flex xs4>
        <v-text-field dark placeholder='値' type='text' v-model='inputValue'></v-text-field>
        <span class='none'>{{ inputValue }}; </span>
      </v-flex>
    </v-layout>

    <v-layout row v-if='data.name === "puts"'>
      <v-flex xs3>
        <span> {{ data.name }}</span>
      </v-flex>
      <v-flex xs9>
        <v-text-field dark placeholder='何か' type='text' v-model='inputValue' />
        <span class='none'>{{ inputValue }}; </span>
      </v-flex>
    </v-layout>
  </drag>
</template>

<script>
import { Drag } from 'vue-drag-drop'
import PuzzSelector from '@/components/playground/PuzzSelector.vue'
import config from '@/config'

const operators = config.components.operators.expressions

export default {
  name: 'puzz-expression',
  props: {
    data: {
      required: true
    }
  },
  data () {
    return {
      inputName: '',
      inputValue: '',
      operators: operators
    }
  },
  methods: {
    dragStart (data, ev) {
      ev.stopPropagation()

      this.$store.commit('question/DRAG_START', {
        dragging: data,
        draggingElement: this.$el
      })
    },
    getColorCode (name) {
      return config.colorCodes[name]
    }
  },
  components: {
    Drag,
    PuzzSelector
  }
}
</script>

<style lang='sass' scoped>
.expression
  min-height: 25px
</style>
