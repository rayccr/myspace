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
                    <button type="button" class="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#update-sign-btn">修改签名</button>
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
                            <button @click="updatesign" type="button" class="btn btn-primary">保存</button>
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

export default{
    name: "UserInfoBase",
    components:{
        ContentBase,
        VAceEditor,
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
        ace.config.set(
            "basePath", 
            "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")

        const store = useStore();

        const editInfo = reactive({
            content: "",
        });


        const updatesign = () => {
            $.ajax({
              url: "http://127.0.0.1:3000/user/updatesign/",
              type: "post",
              data: {
                  userId: store.state.user.id,
                  content: editInfo.content,
              },
              headers: {
                  'Authorization': "Bearer " + store.state.user.token,
              },
              success(resp) {
                    console.log(resp);
                    editInfo.content = "";
                    Modal.getInstance("#update-sign-btn").hide(); //记得id前加#号,class加.(关闭Modal窗口)
                    store.commit("updateUser", resp.value.user);
              }
            });
            
        }

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
            editInfo,
            updatesign,
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