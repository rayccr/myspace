<template>
    <ContentBase>
        <div v-for="post in posts.posts" :key="post.id">
            <div>{{ post.username }} :&nbsp;[ {{ post.content }} ]</div>
        </div>
    </ContentBase>
</template>

<script>
import ContentBase from './ContentBase.vue';

export default{
    name: "ChatBase",
    components:{
        ContentBase,
    },
    props:{
        posts:{
            type: Object,
            required: true,
        },
        user:{
            type: Object,
            required: true,
        },
    },
}

</script>

<style scoped>

</style>
