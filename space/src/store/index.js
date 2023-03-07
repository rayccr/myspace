import { createStore } from 'vuex'
import ModuleUser from './user'
import ModuleUserList from './userList'

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
  }
})
