// import useNavBar from "./index.hooks.ts";
import UserContext from "../../contexts/userContext.ts";
import {useContext} from "react";

const NavBar = (/*{user}: { user: UserInterface | null}*/) => {
    // const {}: {} = useNavBar()
    const user = useContext(UserContext)

    console.log(user)

    return <div style={{
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'end',
        padding: '4px',
        backgroundColor: '#4080ff',
        color: 'white',
    }}>
        {
            user &&
            user.principal &&
            <form method={"post"} action={user.logoutUrl}>
                <span style={{marginRight: "1em"}}>
                    {user.name}
                </span>
                <button type={"submit"}>
                    Logout
                </button>
            </form>
        }
        {
            user &&
            user.principal == null &&
            user.loginUrl &&
            <button onClick={() => window.location.href = user?.loginUrl}>
                Login
            </button>
        }
    </div>
}

NavBar.displayName = "NavBar"

export default NavBar;