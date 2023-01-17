<template>
    <ContentBase>
        User list
    </ContentBase>
</template>

<script>
import ContentBase from "../components/ContentBase.vue";
import $ from 'jquery';
import { ref } from 'vue';


export default{
    name: "UserListView",
    components:{
        ContentBase,
    },

    setup(){
        let users = ref([]);

        $.ajax({
            url: "https://app165.acapp.acwing.com.cn/myspace/userlist/",
            type: "get",
            success(resp){
                users.value = resp;
            }
        })

        return {
            users,
        };
        
    },

}

</script>

<style scoped>

</style>