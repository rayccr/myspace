import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ChatView from '../views/ChatView';
import LoginView from '../views/LoginView';
import RegisterView from '../views/RegisterView';
import UserListView from '../views/UserListView';
import NotFoundView from '../views/NotFoundView';
import UserInfo from "../views/UserInfoView";


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/chat',
    name: 'chat',
    component: ChatView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/userlist',
    name: 'userlist',
    component: UserListView
  },
  {
    path: '/userinfo',
    name: 'userinfo',
    component: UserInfo
  },
  {
    path: '/404',
    name: '404',
    component: NotFoundView
  },


]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
