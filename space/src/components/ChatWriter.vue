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


export default{
    name: "ChatWriter",
    setup(props, context){
        let content = ref('');

        const post_a_post = () =>{
            context.emit('post_a_post', content.value);
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
