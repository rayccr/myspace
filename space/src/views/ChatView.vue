<template>


    <div class="row-1">
        <div class="container">
            <div class="card">
                <div class="card-body" style="color:darkolivegreen; font-weight: bolder;">
                    在线人员:
                    <span v-for="(onlineUser, index) in onlineUsers" :key="index" style="color:goldenrod;">
                        {{ onlineUser }};
                    </span>
                </div>
            </div>
        </div>
        
    </div>

    <div class="row-9">
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
import { ref } from "vue";

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
        let onlineUsers = ref([]);
        
        // const socketUrl = `wss://app3648.acapp.acwing.com.cn/websocket/${store.state.user.token}/`;
        const socketUrl = `ws://127.0.0.1:3000/websocket/${store.state.user.token}/`;


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
            
            $.ajax({
                url: "https://app3648.acapp.acwing.com.cn/api/chat/adduser/",
                // url: "http://127.0.0.1:3000/api/chat/adduser/",
                type: "post",
                data:{
                    username: store.state.user.username,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    console.log(resp);
                }
            });


        });

        onUnmounted(() => {
            /*
            当用户直接关闭浏览器或离开页面时，可能无法触发 Vue.js 的 onUnmounted 钩子函数或其他类似的生命周期钩子函数。
            这是因为在这些情况下，浏览器直接终止了页面的执行，无法执行任何 JavaScript 代码。
            该问题需要在后端判断，解决userPresenceService.removeUser(this.user.getUsername()); // 后端从redis删去该用户
            */
            $.ajax({
                url: "https://app3648.acapp.acwing.com.cn/api/chat/removeuser/",
                // url: "http://127.0.0.1:3000/api/chat/removeuser/",
                type: "post",
                data:{
                    username: store.state.user.username,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    console.log(resp);
                }
            });
            clearInterval(getOnlineUsers);
            console.log("web_socket_discountect");
            socket.close();
        });


        /*
        当用户直接关闭浏览器或离开页面时，可能无法触发 Vue.js 的 onUnmounted 钩子函数或其他类似的生命周期钩子函数。
        这是因为在这些情况下，浏览器直接终止了页面的执行，无法执行任何 JavaScript 代码。
        该问题需要在后端判断，解决userPresenceService.removeUser(this.user.getUsername()); // 后端从redis删去该用户
        */
        window.addEventListener('beforeunload', function() {
            $.ajax({
                url: "https://app3648.acapp.acwing.com.cn/api/chat/removeuser/",
                // url: "http://127.0.0.1:3000/api/chat/removeuser/",
                type: "post",
                data:{
                    username: store.state.user.username,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    console.log(resp);
                }
            });
            clearInterval(getOnlineUsers);
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

        const getOnlineUsers = setInterval(() => {
            // onlineUsers
            $.ajax({
            url: "https://app3648.acapp.acwing.com.cn/api/chat/getonlineusers/",
                // url: "http://127.0.0.1:3000/api/chat/getonlineusers/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    onlineUsers.value = resp.onlineUsers;
                }
            });

        }, 1000);


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
            getOnlineUsers,
            onlineUsers,
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