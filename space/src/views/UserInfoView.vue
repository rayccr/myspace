<template>

    <UserInfoBase @follow="follow" @unfollow="unfollow" :user="user">
    </UserInfoBase>


</template>

<script>
import { reactive } from 'vue';
import UserInfoBase from '../components/UserInfoBase.vue'

export default{
    name:"UserInfoView",
    components:{
        UserInfoBase,
    },

    setup() {
        const user = reactive({
            id: 1,
            username: "ray",
            followerCount: 2023,
            is_follow: false,
            signature: "这个人很懒"
        });

        const follow = () => {
            if(user.is_follow)return;
            user.is_follow = true;
            user.followerCount++;

        }

        const unfollow = () => {
            if(!user.is_follow)return;
            user.is_follow = false;
            user.followerCount--;
        }

        return{
            user,
            follow,
            unfollow,
        }
    }
}   

</script>

<style scoped>
</style>