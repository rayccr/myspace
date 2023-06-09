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
                    <button @click="follow" v-if="!hadFollow && !userInfo.is_me" class="btn btn-outline-primary" type="button">+关注</button>
                    <button @click="unfollow" v-if="hadFollow && !userInfo.is_me" class="btn btn-outline-danger" type="button">取消关注</button>
                </div>


                <div class="d-grid gap-1 col-6 mx-auto">
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

                <div class="row">

                    <ContentBase v-if="userInfo.is_me" class="col-6">
                    <div style="text-align: center; font-weight: bolder; font-size: 30px;">关注的人</div>
                        <div class="card" style="margin-top: 10px;" v-for="(followUsername, index) in followsUsername" :key="index" 
                            @click="open_user_info(followUsername)">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-12">
                                        <img style="width: 50px;" class="img-fluid" :src="followsPhoto[index]" alt="">   
                                        <div> 用户名：{{ followUsername }}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </ContentBase>
                    
                    <ContentBase v-if="userInfo.is_me" class="col-6">
                        <div style="text-align: center; font-weight: bolder; font-size: 30px;">粉丝</div>
                        <div class="card" style="margin-top: 10px;" v-for="(followUsername, index) in followedUsername" :key="index" 
                            @click="open_user_info(followUsername)">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-12">
                                        <img style="width: 50px;" class="img-fluid" :src="followedPhoto[index]" alt="">   
                                        <div> 用户名：{{ followUsername }}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </ContentBase>

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
import router from '@/router/index';


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
        const userId = route.params.userId;
        const editInfo = reactive({
            content: "",
        });
        let hadFollow = ref('');
        let followsUsername = ref([]);
        let followsPhoto = ref([]);
        let followedUsername = ref([]);
        let followedPhoto = ref([]);

        $.ajax({
            url: "https://app3648.acapp.acwing.com.cn/api/user/getuserinfo/",
            // url: "http://127.0.0.1:3000/api/user/getuserinfo/",
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
                if(userId === store.state.user.id){
                    userInfo.value.is_me = true;
                } else {
                    userInfo.value.is_me = false;
                }
            }
        });


        $.ajax({
              url: "https://app3648.acapp.acwing.com.cn/api/user/hadfollow/",
            //   url: "http://127.0.0.1:3000/api/user/hadfollow/",

              type: "post",
              data: {
                  user1Id: store.state.user.id,
                  user2Id: userId,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success(resp) {
                    if(resp.error_message === 'success'){
                        hadFollow.value = true;
                    }else{
                        hadFollow.value = false;
                    }
              }
        });


        $.ajax({
          url: "https://app3648.acapp.acwing.com.cn/api/user/whoifollowed/",
            // url: "http://127.0.0.1:3000/api/user/whoifollowed/",

            type: "post",
            data: {
                userId: store.state.user.id,
                },
            headers: {
                'Authorization': "Bearer " + store.state.user.token,
            },
            success(resp) {
                followsUsername.value = resp.followsUsername;
                followsPhoto.value = resp.followsPhoto;
            }
        });


        $.ajax({
          url: "https://app3648.acapp.acwing.com.cn/api/user/whofollowedme/",
            // url: "http://127.0.0.1:3000/api/user/whofollowedme/",

            type: "post",
            data: {
                userId: store.state.user.id,
                },
            headers: {
                'Authorization': "Bearer " + store.state.user.token,
            },
            success(resp) {
                followedUsername.value = resp.followedUsername;
                followedPhoto.value = resp.followedPhoto;
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
                  user1Id: store.state.user.id,
                  user2Id: userId,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success(resp) {
                console.log(resp.error_message);
              }
            });            
            hadFollow.value = true;
        };


        const unfollow = () =>{
            $.ajax({
              url: "https://app3648.acapp.acwing.com.cn/api/user/unfollowsomeone/",
            //   url: "http://127.0.0.1:3000/api/user/unfollowsomeone/",

              type: "post",
              data: {
                  user1Id: store.state.user.id,
                  user2Id: userId,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success(resp) {
                console.log(resp.error_message);
              }
            });
            hadFollow.value = false;
        };
        

        const open_user_info = (username) => {
            $.ajax({
              url: "https://app3648.acapp.acwing.com.cn/api/user/getuserinfobyname/",
            //   url: "http://127.0.0.1:3000/api/user/getuserinfobyname/",

              type: "post",
              data: {
                    username: username,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success(resp) {
                    console.log(resp.userInfo.id);
                    let userId = resp.userInfo.id;
                    router.push({
                        name: "userinfo",
                        params: {
                            userId,
                        }
                    })
                }
            });
        }

        return {
            userInfo,
            editInfo,
            updateSign,
            follow,
            unfollow,
            hadFollow,
            followsUsername,
            followsPhoto,
            followedUsername,
            followedPhoto,
            open_user_info,
        };
    },
}

</script>

<style scoped>

.card:hover {
  box-shadow: 10px 10px 30px lightgrey;
  transition: 500ms;
}


</style>