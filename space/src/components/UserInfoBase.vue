<template>
    <ContentBase>
        <div class="row">
            <div class="col-3">
                <div class="card">
                    <div class="card-body">
                        <img class="img-fluid" :src="userInfo.photo" alt="">
                    </div>
                </div>
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body">
                        <div>签名: {{ userInfo.signature }}</div>
                    </div>
                </div>
            </div>
            <div class="col-9">
                <div class="username" style="text-align: center;">用户名:{{ userInfo.username }}</div>
                <div class="d-grid gap-1 col-6 mx-auto" style="margin-top: 80px;">
                    <button @click="follow" v-if="userInfo.flag" class="btn btn-outline-primary" type="button">+关注</button>
                    <button @click="unfollow" v-if="!userInfo.flag" class="btn btn-outline-danger" type="button">取消关注</button>
                </div>

            </div>
        </div>
    </ContentBase>

</template>

<script>
import ContentBase from '@/components/ContentBase.vue';
import $ from "jquery";
import { useStore } from 'vuex';

export default{
    name: "UserInfoBase",
    components:{
        ContentBase,
    },
    props: {
        userInfo:{
            type: Object,
            required: true,
        },
        flag:{
            type: Object,
            required: true,
        },
    },

    setup (props, context){

        const store = useStore();
           


        const follow = () => { 
            $.ajax({
              url: "http://127.0.0.1:3000/user/followsomeone/",
              type: "post",
              data: {
                  userId: props.userInfo.id,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success() {
                context.emit('follow');
              }

            });            
        };


        const unfollow = () =>{
    
            $.ajax({
              url: "http://127.0.0.1:3000/user/unfollowsomeone/",
              type: "post",
              data: {
                  userId: props.userInfo.id,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success() {
                 context.emit('unfollow');
              }
            });
        };
        
        return {
            follow,
            unfollow,
        };
    },
}

</script>

<style scoped>


.username {
    font-size:32px;
    font-weight: bold;
}

.fans {
    font-size: 25px;
    color: gray;
    margin-top: 30px;
}

button {
    padding: 5px 5px;
    
}

</style>