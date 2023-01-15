<template>

    <div class="row-10">
        <ContentBase>
            <ChatBase :posts="posts" :user="user">
            </ChatBase>
        </ContentBase>
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

export default{
    name: "ChatView",
    components:{
        ContentBase,
        ChatBase,
        ChatWriter,
},

setup() {
    const posts = reactive({
        count: 5,
        posts: [
            {
                id: 1,
                userId: 1,
                content: "hahaha",
            },
            {
                id: 2,
                userId: 1,
                content: "xixixixi",
            },
            {
                id: 3,
                userId: 1,
                content: "这是1条消息",
            },
            {
                id: 3,
                userId: 1,
                content: "这是2条消息",
            },
            {
                id: 3,
                userId: 1,
                content: "这是3条消息",
            },
        ]
    });

    const user = reactive({
            id: 1,
            username: "ray",
            followerCount: 2023,
            is_follow: false,
            signature: "这个人很懒"
    });

    const post_a_post = (content) => {
        posts.count++;
        posts.posts.push({
            id: 4,
            userId: 1,
            content: content,
        });
    }


    return{
        posts,
        user,
        post_a_post,
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

</style>