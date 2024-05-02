<script setup>
  import {ref} from "vue";

  const result = ref([]);
  const msg = ref('');
  const sendMsg = () => {
    // sse: 服务端推送 Server-Sent Events
    let source = new EventSource(`http://localhost:8080/ai/generateStreamAsString?message=${msg.value}`);
    let count = 0;
    source.onmessage = (event) => {
      //console.log(event.data);
      // 服务端返回的每个数据之间会有一个空，如果连续两个空表示服务端数据结束
      // EventSource 不能正确感知服务端数据结束，导致持续找服务端索要数据要流
      if (event.data === '') {
        count++;
      }
      if (count === 2) {
        // 关闭流
        event.close();
      }
      result.value.push(event.data);
    }
  }
</script>

<template>
  <div id="container">
    <div id="history">
      <span v-for="(r, i) in result" :key="i">{{r}}</span>
    </div>
    <div id="chat">
      <textarea v-model="msg"></textarea>
      <button @click="sendMsg">Send</button>
    </div>
  </div>
</template>

<style scoped>
  * {
    margin: 0;
    padding: 0;
  }
  textarea {
    width: 800px;
    height: 80px;
  }
  button {
    width: 800px;
    height: 50px;
  }
  #container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    background-color: #f9f9f9;
    width: 800px;
    height: auto;
    border: 1px solid black;
  }
  #history {
    background-color: antiquewhite;
    width: 800px;
    height: 500px;
  }
  #chat {
    background-color: dodgerblue;
    width: 800px;
    height: 140px;
  }
</style>
