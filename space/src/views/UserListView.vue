<template>
  <ContentBase>
      <div class="card" v-for="user in users" :key="user.id" @click="open_user_info(user.id)">
        <div class="card-body">
          <div class="row">
            <div class="col-1 img-field">
              <img class="img-fluid" :src="user.photo" alt="">
            </div>
            <div class="col-11">
              <div class="username">{{ user.username }}</div>
              <div class="fans-count">粉丝数: {{ user.followCount }}</div>
            </div>
          </div>
        </div>
      </div>
  </ContentBase>
</template>

<script>
import ContentBase from '../components/ContentBase'
import $ from 'jquery';
import { useStore } from 'vuex';
import { ref } from 'vue';
import router from '@/router/index';

export default {
  name: 'UserList',
  components: {
      ContentBase,
  },
  setup() {

    const store = useStore();
    let users = ref([]);

    $.ajax({
      url: "https://app3648.acapp.acwing.com.cn/api/user/userlist/",
      // url: "http://127.0.0.1:3000/api/user/userlist/",

      type: "get",
      headers: {
            Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        users.value = resp.users;
      }
    });

    const open_user_info = (userId) => {
        if(store.state.user.is_login){
          router.push({
            name: "userinfo",
            params: {
              userId
            }
          })
        } else {
          router.push({
            name: "login",  
          });
        }
    }


    return {
      users,
      open_user_info,
    };
  }
}
</script>

<style scoped>
img {
  border-radius: 50%;
}

.username {
  font-weight: bold;
  height: 50%;
}

.follower-count {
  font-size: 12px;
  color: gray;
  height: 50%;
}

.card {
  margin-bottom: 20px;
  cursor: pointer;
}

.card:hover {
  box-shadow: 2px 2px 10px lightgrey;
  transition: 500ms;
}

.img-field {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.fans-count{
    color: darkgray;
    font-size: 13px;
}

</style>
