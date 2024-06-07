// import useHomePage from "./index.hooks.ts";
import NavBar from "../../components/NavBar";

const HomePage = (/*{user}: {user : UserInterface | null}*/) => {
    // const {}: {} = useHomePage()

    return <div>
        <NavBar/>
        <p>Hello from Home!</p>
    </div>
}

HomePage.displayName = "HomePage"

export default HomePage