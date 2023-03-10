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
                <div v-if="userInfo.is_me" class="fans" style="text-align: center;">粉丝数:{{ userInfo.followCount }}</div>
                <div class="d-grid gap-1 col-6 mx-auto" style="margin-top: 80px;">
                    <button @click="follow" v-if="userInfo.flag && !userInfo.is_me" class="btn btn-outline-primary" type="button">+关注</button>
                    <button @click="unfollow" v-if="!userInfo.flag && !userInfo.is_me" class="btn btn-outline-danger" type="button">取消关注</button>
                </div>


                <div class="d-grid gap-1 col-6 mx-auto" style="margin-top: 17px;">
                    <button v-if="userInfo.is_me" type="button" class="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#update-sign-btn">修改签名</button>
                </div>


                <!-- Modal -->
                <div class="modal fade modal-lg" id="update-sign-btn" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="update-sign-btn">编辑</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <VAceEditor
                                v-model:value="editInfo.content"
                                @init="editorInit"
                                lang="c_cpp"
                                theme="textmate"
                                style="height: 300px" />
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                            <button @click="updateSign" type="button" class="btn btn-primary">保存</button>
                        </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </ContentBase>

</template>

<script>
import ContentBase from '@/components/ContentBase.vue';
import $ from "jquery";
import { useStore } from 'vuex';
import { VAceEditor } from 'vue3-ace-editor';
import {reactive} from 'vue'
import { Modal } from 'bootstrap/dist/js/bootstrap';
import ace from 'ace-builds';
import { ref } from 'vue';
import { useRoute } from 'vue-router';

export default{
    name: "UserInfoView",
    components:{
        ContentBase,
        VAceEditor,
    },

    setup (){
        ace.config.set(
            "basePath", 
            "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")

        const store = useStore();
        let userInfo = ref([]);
        const route = useRoute();
        const userId = parseInt(route.params.userId);
        const editInfo = reactive({
            content: "",
        });


        $.ajax({
            url: "https://app3648.acapp.acwing.com.cn/api/user/getotherinfo/",
            // url: "http://127.0.0.1:3000/api/user/getotherinfo/",

            type: "post",
            data:{
                userId: userId,
            },            
            headers: {
                Authorization: "Bearer " + store.state.user.token,
            },
            success(resp) {
                userInfo.value = resp.userInfo;
                editInfo.content = resp.userInfo.signature;
                if(userId === parseInt(store.state.user.id)){
                    userInfo.value.is_me = true;
                } else {
                    userInfo.value.is_me = false;
                }
            }
        });

        $.ajax({
              url: "https://app3648.acapp.acwing.com.cn/api/user/followsomeone/",
            //   url: "http://127.0.0.1:3000/api/user/followsomeone/",

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


        const updateSign = () => {
            $.ajax({
              url: "https://app3648.acapp.acwing.com.cn/api/user/updatesign/",
            //   url: "http://127.0.0.1:3000/api/user/updatesign/",

              type: "post",
              data: {
                  userId: store.state.user.id,
                  content: editInfo.content,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success() {
                    Modal.getInstance("#update-sign-btn").hide(); //记得id前加#号,class加.(关闭Modal窗口)
                    userInfo.value.signature = editInfo.content;
                    editInfo.content = "";
              }
            });
            
        }

        const follow = () => { 
            $.ajax({
              url: "https://app3648.acapp.acwing.com.cn/api/user/followsomeone/",
            //   url: "http://127.0.0.1:3000/api/user/followsomeone/",

              type: "post",
              data: {
                  userId: userInfo.value.id,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success() {
                console.log("follow success");
                userInfo.value.flag = !userInfo.value.flag;
              }

            });            
        };


        const unfollow = () =>{
            $.ajax({
              url: "https://app3648.acapp.acwing.com.cn/api/user/unfollowsomeone/",
            //   url: "http://127.0.0.1:3000/api/user/unfollowsomeone/",

              type: "post",
              data: {
                  userId: userInfo.value.id,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success() {
                console.log("unfollow success");
                userInfo.value.flag = !userInfo.value.flag;
              }
            });
        };
        
        return {
            userInfo,
            editInfo,
            updateSign,
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