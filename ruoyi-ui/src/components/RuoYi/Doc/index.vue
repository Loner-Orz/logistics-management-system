<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'RuoYiDoc',
  data() {
    return {
      url: 'https://github.com/Loner-Orz/store/blob/master/%E6%93%8D%E4%BD%9C%E6%96%87%E6%A1%A3.md'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
