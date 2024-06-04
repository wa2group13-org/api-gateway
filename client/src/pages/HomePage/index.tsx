import useHomePage from "./index.hooks.ts";

const HomePage = () => {
    const {performLogin} = useHomePage()

    return <div>
        <p>Hello from home</p>
        <button onClick={performLogin}>LOGIN</button>
    </div>
}

HomePage.displayName = "HomePage"

export default HomePage