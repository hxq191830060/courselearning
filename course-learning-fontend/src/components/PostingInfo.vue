<template>
  <div>
    <v-card width="700" color="#F5F5F5" style="display: flex; flex-direction: column" elevation="5">
      <div  style="width: 90% ;align-self: center">
        <v-card-title>{{ this.postingItem.title }}</v-card-title>
        <v-card-subtitle>用户：{{ this.postingItem.userName}} 发帖时间：{{ this.postingItem.createTime }}</v-card-subtitle>
        <v-card-text>{{ this.postingItem.content }}</v-card-text>
      </div>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "PostingInfo",
  props:{
    postingItem: {
      type: Object,
      // 对象或数组且一定会从一个工厂函数返回默认值
      default: function () {
        return {}
      }
    },
  }
}
</script>

<style scoped>

</style>
