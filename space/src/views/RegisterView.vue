<template>
    <ContentBase>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="register">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder=" input username">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder=" input password">
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">确认密码</label>
                        <input v-model="confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder=" input password again">
                    </div>
                    <div class="error-message">{{error_message}}</div>
                    <button type="submit" class="btn btn-success">register</button>
                </form>
            </div>
        </div>
    </ContentBase>
</template>

<script>
import ContentBase from "../components/ContentBase.vue";
import {ref} from 'vue'    
import router from '@/router/index';
import $ from 'jquery'


export default{
    name: "RegisterView",
    components:{
        ContentBase,
    },

    setup(){
        let username = ref("");
        let password = ref("");
        let confirmPassword = ref("");
        let error_message = ref("");

        const register = () => {
            
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/register/",
                type: "post",
                data: {
                    username: username.value,
                    password: password.value,
                    confirmedPassword: confirmPassword.value,
                },
                success(resp){
                    if(resp.error_message === "success"){
                        router.push({name: "login"});
                    } else {
                        error_message.value = resp.error_message;    
                    }
                },
            });
        }

        return{
            username,
            password,
            confirmPassword,
            error_message,
            register,
        }
    }
}

</script>

<style scoped>
button{
    width: 100%;    
}
div.error-message{
    color: red;
}
</style>