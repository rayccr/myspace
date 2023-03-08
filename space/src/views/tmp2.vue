<template>
    <UserInfoBase @follow="follow" @unfollow="unfollow" :userInfo="userInfo">
    </UserInfoBase>
</template>

<script>
import UserInfoBase from '../components/UserInfoBase.vue'
import $ from "jquery"
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import { ref } from 'vue';

export default{
    name:"UserInfoView",
    components:{
        UserInfoBase,
    },
    setup() {

        let userInfo = ref([]);
        const route = useRoute();
        const store = useStore();
        const userId = parseInt(route.params.userId);

        $.ajax({
            url: "http://127.0.0.1:3000/user/getotherinfo/",
            type: "post",
            data:{
                userId: userId,
            },            
            headers: {
                Authorization: "Bearer " + store.state.user.token,
            },
            success(resp) {
                userInfo.value = resp.userInfo;
                if(userId === parseInt(store.state.user.id)){
                    userInfo.value.is_me = true;
                } else {
                    userInfo.value.is_me = false;
                }
            }
        });

        

        $.ajax({
              url: "http://127.0.0.1:3000/user/followsomeone/",
              type: "post",
              data: {
                  userId: userId,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success(resp) {
                if(resp.error_message === 'success'){
                    userInfo.value.flag = true;
                }else{
                    userInfo.value.flag = false;
                }
              }
            });


        const follow = () => {
            console.log("follow success");
            userInfo.value.flag = !userInfo.value.flag;
        }

        const unfollow = () => {
            console.log("unfollow success");
            userInfo.value.flag = !userInfo.value.flag;
        }
    

        return{
            userInfo,
            follow,
            unfollow,
        }
    }
}   

</script>

<style scoped>
</style>