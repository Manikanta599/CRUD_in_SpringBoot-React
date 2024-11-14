import React, { useState } from 'react';
import './css/home.css';
import { RegistrationData, PasswordUpdateDataandLogin } from './interfaces';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Home: React.FC = () => {
    const [isRegister, setIsRegister] = useState(false);
    const [isForgotPassword, setIsForgotPassword] = useState(false);
    const [role, setRole] = useState('user'); // default role
    const navigate = useNavigate(); 
    const [registrationData, setRegistrationData] = useState<RegistrationData>({
        email: "",
        password: "",
        fName: "",
        lName: "",
        phoneNumber: "",
    });
    const [passwordUpdateData, setPasswordUpdateData] = useState<PasswordUpdateDataandLogin>({
        email: "",
        password: "",
    });
    const [loginData, setLoginData] = useState<PasswordUpdateDataandLogin>({
        email: "",
        password: "",
    });

    const toggleForm = () => {
        setIsRegister(!isRegister);
        setIsForgotPassword(false);
    };

    const handleRoleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setRole(e.target.value);
    };

    const handleForgotPassword = () => {
        setIsForgotPassword(true);
        setIsRegister(false);
    };

    // Handlers for registration, login, and password reset
    const handleRegistrationChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setRegistrationData({
            ...registrationData,
            [e.target.name]: e.target.value,
        });
    };

    const handleLoginChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value,
        });
    };

    const handlePasswordUpdateChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPasswordUpdateData({
            ...passwordUpdateData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log("inside submit");


        try {
            if (isRegister) {
                console.log("Registration details", registrationData);
                const response =await axios.post('http://localhost:8080/auth/register',registrationData);
                if(response.status)
                {
                    window.alert("🎉 Registration successful! Welcome aboard!");
                    navigate(role === 'user' ? '/user' : '/admin');    
                }
                else
                {
                    window.alert("⚠️ Registration Not completed!");
                }
            }
            else if (isForgotPassword) {
                console.log("Password reset details", passwordUpdateData);
                const response=await axios.put('http://localhost:8080/auth/update-password',passwordUpdateData);
                if(response.status)
                {
                    window.alert("🎉 Password Updatation Successful!");
                }else{
                    window.alert("⚠️ Password Updatation Failed!");
                }
            }
            else {
                console.log("Login details", loginData);
                const response=await axios.post('http://localhost:8080/auth/login',loginData);
                if(response.status)
                {
                    window.alert("🎉 Login Successful!")
                    navigate(role === 'user' ? '/user' : '/admin'); // Redirect based on the role

                }else {
                    window.alert("⚠️ Login Failed please Enter correct credentials!")
                }
            }
        } catch(err)
        {
            window.alert("🚫 Bad request. Please check your inputs and try again.");
        }
    };

    return (
        <div className="home-container">
            <div className="form-container">
                <div className="form-header">
                    <h2>
                        {isForgotPassword ? 'Forgot Password' : isRegister ? 'Register' : 'Login'}
                    </h2>
                </div>

                {!isForgotPassword && (
                    <div className="role-buttons">
                        <label>
                            <input
                                type="radio"
                                name="role"
                                value="user"
                                checked={role === 'user'}
                                onChange={handleRoleChange}
                            />
                            User
                        </label>
                        {!isRegister && (
                            <label>
                                <input
                                    type="radio"
                                    name="role"
                                    value="admin"
                                    checked={role === 'admin'}
                                    onChange={handleRoleChange}
                                />
                                Admin
                            </label>
                        )}
                    </div>
                )}

                <div className="form-body">
                    <form onSubmit={handleSubmit}>
                        {isForgotPassword ? (
                            <div className="form-item">
                                <label htmlFor="email">Email</label>
                                <input
                                    type="email"
                                    id="email"
                                    placeholder="Enter your email to reset password"
                                    onChange={handlePasswordUpdateChange}
                                    required
                                    name="email"
                                />
                                <label htmlFor="password">Password</label>
                                <input
                                    type="password"
                                    id="password"
                                    placeholder="Enter new password"
                                    onChange={handlePasswordUpdateChange}
                                    required
                                    name="password"
                                />
                            </div>
                        ) : isRegister && role === 'user' ? (
                            <>
                                <div className="form-item">
                                    <label htmlFor="firstname">First Name</label>
                                    <input
                                        type="text"
                                        id="firstname"
                                        placeholder="Enter your first name"
                                        required
                                        onChange={handleRegistrationChange}
                                        name="fName"
                                    />
                                </div>

                                <div className="form-item">
                                    <label htmlFor="lastname">Last Name</label>
                                    <input
                                        type="text"
                                        id="lastname"
                                        placeholder="Enter your last name"
                                        onChange={handleRegistrationChange}
                                        required
                                        name="lName"
                                    />
                                </div>

                                <div className="form-item">
                                    <label htmlFor="email">Email</label>
                                    <input
                                        type="email"
                                        id="email"
                                        placeholder="Enter your email"
                                        onChange={handleRegistrationChange}
                                        required
                                        name="email"
                                    />
                                </div>

                                <div className="form-item">
                                    <label htmlFor="password">Password</label>
                                    <input
                                        type="password"
                                        id="password"
                                        placeholder="Enter your password"
                                        onChange={handleRegistrationChange}
                                        required
                                        name="password"
                                    />
                                </div>
                                <div className="form-item">
                                    <label htmlFor="phone">Phone Number</label>
                                    <input
                                        type="tel"
                                        id="phone"
                                        placeholder="Enter your phone number"
                                        onChange={handleRegistrationChange}
                                        required
                                        name="phoneNumber"
                                    />
                                </div>
                            </>
                        ) : (
                            <>
                                <div className="form-item">
                                    <label htmlFor="email">Email</label>
                                    <input
                                        type="email"
                                        id="email"
                                        placeholder="Enter your email"
                                        onChange={handleLoginChange}
                                        required
                                        name="email"
                                    />
                                </div>

                                <div className="form-item">
                                    <label htmlFor="password">Password</label>
                                    <input
                                        type="password"
                                        id="password"
                                        placeholder="Enter your password"
                                        onChange={handleLoginChange}
                                        required
                                        name="password"
                                    />
                                </div>
                            </>
                        )}

                        <div className="form-item">
                            <button type="submit" className="submit-button">
                                {isForgotPassword ? 'Reset Password' : isRegister ? 'Register' : 'Login'}
                            </button>
                        </div>
                    </form>
                </div>

                <div className="form-footer">
                    {isForgotPassword ? (
                        <p>
                            Remembered your password?{' '}
                            <span className="register-link" onClick={() => setIsForgotPassword(false)}>
                                Login
                            </span>
                        </p>
                    ) : !isRegister ? (
                        <>
                            <p>
                                Forgot your password?{' '}
                                <span className="forgot-password-link" onClick={handleForgotPassword}>
                                    Forgot Password
                                </span>
                            </p>
                            {role !== 'admin' && (
                                <p>
                                    Don't have an account?{' '}
                                    <span className="register-link" onClick={toggleForm}>
                                        Register
                                    </span>
                                </p>
                            )}
                        </>
                    ) : (
                        <p>
                            Already have an account?{' '}
                            <span className="register-link" onClick={toggleForm}>
                                Login
                            </span>
                        </p>
                    )}
                </div>
            </div>
        </div>
    );
};

export default Home;
