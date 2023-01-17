<template>
    <nav class="navbar bg-primary navbar-expand-lg navbar-dark">
        <div class="container">
            <router-link class="navbar-brand" :to="{name: 'home'}">Space</router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link :class="route_name == 'home' ? 'nav-link active' : 'nav-link'" :to="{name: 'home'}">Home</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="route_name == 'userlist' ? 'nav-link active' : 'nav-link'" :to="{name: 'userlist'}">用户列表</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="route_name == 'chat' ? 'nav-link active' : 'nav-link'" :to="{name: 'chat'}">Chat空间</router-link>
                    </li>
                </ul>
                
                <ul class="navbar-nav" v-if="$store.state.user.is_login">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            {{$store.state.user.username }}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li>
                            <router-link class="dropdown-item" :to="{name: 'userinfo'}">个人信息</router-link>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <router-link class="dropdown-item" :to="{name: 'login'}" @click="logout">退出</router-link>
                        </li>
                        </ul>
                    </li>
                </ul>

                <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'login'}" role="button">
                        登录
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'register'}" role="button">
                        注册
                        </router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>


</template>

<script>
import { useStore } from 'vuex';
import { computed } from "@vue/reactivity";
import { useRoute } from 'vue-router';

export default{
    name: "NavBar",

    setup(){
        const store = useStore();
        const router = useRoute();
        let route_name = computed(() => router.name);
        
        const logout = () => {
            store.dispatch("logout");
        }

        return {
            route_name,
            logout,
        }
    }


}

</script>

<style scoped>

</style>
