import { createStore } from 'vuex'
import ModuleUser from './user'
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
    chat: ModuleChat,
  }
})
