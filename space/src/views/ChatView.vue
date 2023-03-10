<template>
    <div class="row-10">
            <div class="container">
                <div class="card">
                    <div class="card-body pre-scrollable myscroll container">
                        <ChatBase :posts="posts">
                        </ChatBase>
                    </div>
                </div>
            </div>        

    </div>
    <div class="row-2 edit-field">
        <ContentBase>
            <ChatWriter @post_a_post="post_a_post" :posts="posts">

            </ChatWriter>
        </ContentBase>
    </div>
</template>

<script>
import { reactive } from "vue";
import ContentBase from "../components/ContentBase.vue";
import ChatBase from "../components/ChatBase.vue";
import ChatWriter from "../components/ChatWriter.vue";
import { useStore } from "vuex";
import $ from "jquery";
import {onMounted, onUnmounted } from 'vue'

export default{
    name: "ChatView",
    components:{
        ContentBase,
        ChatBase,
        ChatWriter,
},

    setup() {

        const store = useStore();
        const posts = reactive({
            count: 0,
            posts: {},
        });
        
        const socketUrl = `wss://app3648.acapp.acwing.com.cn/websocket/${store.state.user.token}/`;
        // const socketUrl = `ws://127.0.0.1:3000/websocket/${store.state.user.token}/`;


        let socket = null;

        onMounted(() => {
            socket = new WebSocket(socketUrl);
            
            socket.onopen = () => {
                console.log("web_connected!");
                store.commit("updateSocket", socket);
            }

            socket.onmessage = msg => {
                const data = JSON.parse(msg.data);
                console.log(data);
                get_a_post(data["username"], data["content"]);
            }

            socket.onclose = () => {
                console.log("web_disconnected!");
            }
        });

        onUnmounted(() => {
            socket.close();
        });


        $.ajax({
            url: "https://app3648.acapp.acwing.com.cn/api/chat/getmessage/",
            // url: "http://127.0.0.1:3000/api/chat/getmessage/",
            type: "post",
            headers: {
                Authorization: "Bearer " + store.state.user.token,
            },
            success(resp) {
                console.log(resp);
                posts.posts = resp.blocks;
            }
        });

        const post_a_post = (content) => {
            posts.count++;
            console.log("post:" + posts.count);
            posts.posts.unshift({
                id: posts.count,
                username: store.state.user.username,
                content: content,
            });
            content = ""; 
        };

        const get_a_post = (username, content) => {
            if(username === store.state.user.username){
                console.log("xxx");
                return;
            }
            
            posts.posts.unshift({
                id: posts.count,
                username: username,
                content: content,
            });
            
            console.log("get" + posts.count);
            content = "";
        }

        return{
            posts,
            post_a_post,
            get_a_post,
        }
    }


}

</script>

<style scoped>
.edit-field{
    position: absolute;
    bottom: 0px;
    width: 100%;
}

.myscroll{
    color: brown;
    overflow:auto;
    height:65vh;
    width: 80vw;
    position:absolute;
}

</style>