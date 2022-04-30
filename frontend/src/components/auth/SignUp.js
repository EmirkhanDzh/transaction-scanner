import { useForm } from "react-hook-form";
import { Link, useNavigate, useParams } from "react-router-dom";
import { yupResolver } from '@hookform/resolvers/yup'
import * as Yup from 'yup'
import "./Auth.css";

function SignUp(props) {

    const {id} = useParams();
    console.log(id)
    const navigate = useNavigate();

    const onSubmit = async (data) => {
        const user = {
            chatId: id,
            username: data.username,
            password: data.password
        }
        await props.signUp(user, "ok")
        navigate("/")
        window.location.reload();
    };

    const TELEGRAM_USERNAME = "https://telegram.org/faq#q-what-are-usernames-how-do-i-get-one"
    const TELEGRAM_BOT = "http://t.me/nur312_bot"

    const formSchema = Yup.object().shape({
        password: Yup.string()
            .required('Password is required')
            .min(4, 'Password length should be at least 4 characters'),
        passwordConfirm: Yup.string()
            .required('Confirm Password is required')
            .oneOf([Yup.ref('password')], 'Passwords must and should match'),
    })

    const validationOpt = { resolver: yupResolver(formSchema) }

    const { register, handleSubmit, reset, formState } = useForm(validationOpt)

    const { errors } = formState

    console.log(errors);

    return (
        <div className="authContainer">
            <p className="invisible">Prrr</p>
            <form onSubmit={handleSubmit(onSubmit)}>
                <h2>Sign up for Notiprice</h2>
                <div className="ui divider"></div>
                <div className="ui form">
                    <div className="field">
                        <label>Your username</label>
                        <input
                            type="text"
                            placeholder="Username"
                            {...register("username", {
                                required: "Username is required",
                                minLength: {
                                    value: 5,
                                    message: "Username must be more than 5 characters",
                                }
                            })}
                        />
                    </div>
                    <p className="authP">{errors?.username?.message}</p>

                    <div className="field">
                        <label>Password</label>
                        <input
                            name="password"
                            type="password"
                            placeholder="Password"
                            {...register("password")}
                        />
                    </div>
                    <p className="authP">{errors?.passwordConfirm?.message}</p>

                    <div className="field">
                        <label>Password Confirmation</label>
                        <input
                            name="passwordConfirm"
                            type="password"
                            placeholder="Password Confirmation"
                            {...register("passwordConfirm")}
                        />
                    </div>
                    <p className="authP">{errors?.passwordConfirm?.message}</p>
                </div>
                <button className="fluid ui button blue">Sign Up</button>
            </form >
            <Link to="/sign-in" className="signInUpFooter">
                Already on Notiprice? <u>Sign In</u>
            </Link>
        </div >
    );
}

export default SignUp;
