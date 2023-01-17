import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ChatView from '../views/ChatView';
import LoginView from '../views/LoginView';
import RegisterView from '../views/RegisterView';
import UserListView from '../views/UserListView';
import NotFoundView from '../views/NotFoundView';
import UserInfo from "../views/UserInfoView";
import store from '@/store';


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/chat/',
    name: 'chat',
    component: ChatView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/login/',
    name: 'login',
    component: LoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/register/',
    name: 'register',
    component: RegisterView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/userlist/',
    name: 'userlist',
    component: UserListView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/userinfo/',
    name: 'userinfo',
    component: UserInfo,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/404/',
    name: '404',
    component: NotFoundView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/",
  },

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if(to.meta.requestAuth && !store.state.user.is_login){
    next({name: "login"});
  }else{
    next();
  }
});

export default router
