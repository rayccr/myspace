<template> 
    <div class="form-floating edit-field">
        <textarea v-model="content" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
        <label for="floatingTextarea2">Say something...</label>
    </div>
    <div class="d-grid">
        <button @click="post_a_post" class="btn btn-primary" type="button">发送</button>
    </div>

</template>

<script>
import { ref } from 'vue';
import { useStore } from "vuex";
import $ from "jquery";

export default{
    name: "ChatWriter",

    setup(props, context){
        
        const store = useStore();

        let content = ref('');

        const post_a_post = () =>{
            context.emit('post_a_post', content.value);

            $.ajax({
                url: "https://app3648.acapp.acwing.com.cn/api/chat/addmessage/",
                // url: "http://127.0.0.1:3000/api/chat/addmessage/",
                type: "post",
                data:{
                    username: store.state.user.username,
                    content: content.value,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    console.log(resp);
                }
            });

            store.state.chat.socket.send(JSON.stringify({
                username: store.state.user.username,
                content: content.value,
            }));

            content.value = "";
        }

        return {
            content,
            post_a_post,
        }
    }
}

</script>

<style scoped>

.edit-field{
    margin-bottom: 25px;
}

</style>
