<template>
  <NavBar></NavBar>
  <router-view/>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap';
import NavBar from './components/NavBar.vue';
// import $ from "jquery";


export default{
  name: "App",
  components: {
    NavBar,
  },

  // setup(){
  //   $.ajax({
  //     url: "http://127.0.0.1:3000/user/account/info/",
  //     type: "post",
  //     data:{
  //       username: 'ray',
  //       password: 123,
  //     },
  //     success(resp){
  //       console.log(resp);
  //     },
  //     error(resp){
  //       console.log(resp);
  //     }
  //   });
  // }

    


    // $.ajax({
    //   url: "http://127.0.0.1:3000/user/account/register/",
    //   type: "post",
    //   data: {
    //     username: "zxc",
    //     password: "zxc",
    //     confirmedPassword: "zxc",
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(){
    //     console.log("error");
    //   }

    // });


  // }

}


</script>

<style>

</style>
