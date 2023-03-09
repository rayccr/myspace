import { createStore } from 'vuex'
import ModuleUser from './user'
import ModuleUserList from './userList'
import ModuleChat from './chat'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user: ModuleUser,
    userList: ModuleUserList,
    chat: ModuleChat,
  }
})
