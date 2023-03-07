
export default ({
    state: {
        users: [],
        users_count: 0
    },
    getters: {
    },
    mutations: {
        updateUserList(state, data){
            state.users = data.users,
            state.users_count = data.users_count 
        }
    },
    actions: {
    },
    modules: {
    }
})
