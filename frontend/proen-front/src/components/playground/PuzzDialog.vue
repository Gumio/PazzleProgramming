<template>
  <v-dialog
    v-model='$store.state.question.dialog'
    width='400'
    class='dialog'
  >
    <v-card
      width='400'
    >
      <v-card-title>
        <h1>Success!</h1>
      </v-card-title>
      <v-card-text>
        <v-layout justify-center class='star'>
          <v-icon class='star-correct' key='on' color='orange' v-if='isCorrect'>fas fa-star</v-icon>
          <v-icon class='star-failed' key='off' color='grey' v-else>far fa-star</v-icon>
        </v-layout>
      </v-card-text>
      <v-layout justify-center>
        <v-card-text>
          {{ text }}
        </v-card-text>
      </v-layout>
      <v-layout justify-center>
        <v-card-actions>
          <v-btn flat color='blue'>back</v-btn>
          <v-btn flat color='blue'>next</v-btn>
        </v-card-actions>
      </v-layout>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'puzz-dialog',
  props: {
    text: {
      type: String,
      required: true,
      default: ''
    },
    isCorrect: {
      type: Boolean,
      required: true,
      default: false
    }
  }
}
</script>

<style lang='sass' scoped>
.star
  i
    font-size: 50px
  &-correct
    animation: starCorrect 3s ease 1
    @keyframes starCorrect
      0%
        opacity: .1
        transform: scale(.1, .1) rotate(0deg)
      100%
        opacity: 1
        transform: scale(1, 1) rotate(720deg)
  &-failed
    animation: starFailed 1s ease 1
    @keyframes starFailed
      0%
        opacity: .1
        transform: scale(.1, .1)
      100%
        opacity: 1
        transform: scale(1, 1)
</style>
