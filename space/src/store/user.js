import $ from "jquery"

export default ({
  state: {
    id: "",
    username: "",
    photo: "",
    token: "",
    is_login: false,
    follow_count: 0,
    signature: "",
    ip: "",
    pulling_info: true,
  },

  getters: {
  },

  mutations: {
    updateUser(state, user){
        state.id = user.id;
        state.username = user.username;
        state.photo = user.photo;
        state.follow_count = user.follow_count;
        state.signature = user.signature;
        state.ip = user.ip;
        state.is_login = user.is_login;
    },
    updateToken(state, token){
        state.token = token;
    },
    logout(state){
        state.id = "";
        state.username = "";
        state.photo = "";
        state.token = "";
        state.is_login = false;
        state.follow_count = 0;
        state.signature = "";
        state.ip = "";
    },
    updatePullingInfo(state, pulling_info){
        state.pulling_info = pulling_info;
    },
  },

  actions: {
    login(context, data){
        $.ajax({
            url: "https://app3648.acapp.acwing.com.cn/api/user/account/token/",
            // url: "http://127.0.0.1:3000/api/user/account/token/",
            type: "post",
            data:{
              username: data.username,
              password: data.password,
            },
            success(resp){
                if(resp.error_message === "success"){
                    localStorage.setItem("jwt_token", resp.token);
                    context.commit("updateToken", resp.token);
                    data.success(resp);
                } else{
                    data.error(resp);
                }
            },
            error(resp){
                data.error(resp);
            }
          });
    },
    
    getinfo(context, data){
        $.ajax({
            url: "https://app3648.acapp.acwing.com.cn/api/user/account/info/",
            // url: "http://127.0.0.1:3000/api/user/account/info/",
            type: "get",
            headers: {
                Authorization: "Bearer " + context.state.token,
            },
            success(resp){
                if(resp.error_message === "success"){
                    context.commit("updateUser", {
                        ...resp,
                        is_login: true,
                    });
                    data.success(resp);
                }else{
                    data.error(resp);    
                }
            },
            error(resp){
                data.error(resp);
            },
        });
    },
    logout(context){
        localStorage.removeItem("jwt_token");
        context.commit("logout");
    },
  },

  modules: {
  }
})
