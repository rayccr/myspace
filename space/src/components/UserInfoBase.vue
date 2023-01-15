<template>
    <ContentBase>
        <div class="row">
            <div class="col-3">
                <div class="card">
                    <div class="card-body">
                        <img class="img-fluid" src="https://cdn.acwing.com/media/user/profile/photo/161051_lg_2c0b96df8c.jpg" alt="">
                    </div>
                </div>
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body">
                        <div>签名: {{ user.signature }}</div>
                    </div>
                </div>
            </div>
            <div class="col-9">
                <div class="username" style="text-align: center;">用户名:{{ user.username }}</div>
                <div class="fans" style="text-align: center;">粉丝:{{ user.followerCount }}</div>
                <div class="d-grid gap-1 col-6 mx-auto" style="margin-top: 50px;">
                    <button @click="follow" v-if="!user.is_follow" class="btn btn-outline-primary" type="button">+关注</button>
                    <button @click="unfollow" v-if="user.is_follow" class="btn btn-outline-danger" type="button">取消关注</button>
                </div>

            </div>
        </div>
    </ContentBase>

</template>

<script>
import ContentBase from '@/components/ContentBase.vue';


export default{
    name: "UserInfoBase",
    components:{
        ContentBase,
    },
    props: {
        user:{
            type: Object,
            required: true,
        },
    },

    setup (props, context){
        
        const follow = () => {
            context.emit('follow');
        };
        const unfollow = () =>{
            context.emit('unfollow');
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