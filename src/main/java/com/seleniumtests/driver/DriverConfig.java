/*
 * Copyright 2015 www.seleniumtests.com
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seleniumtests.driver;

import java.net.URISyntaxException;

import java.util.ArrayList;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class DriverConfig {

    private boolean setAssumeUntrustedCertificateIssuer = true;
    private boolean setAcceptUntrustedCertificates = true;
    private boolean enableJavascript = true;
    private WebDriver driver;
    private BrowserType browser = BrowserType.FireFox;
    private DriverMode mode = DriverMode.LOCAL;
    private String hubUrl;
    private String ffProfilePath;
    private String operaProfilePath;
    private String ffBinPath;
    private String ieDriverPath;
    private String chromeDriverPath;
    private String chromeBinPath;
    private int webSessionTimeout = 90 * 1000;
    public static final int DEFAULT_IMPLICIT_WAIT_TIMEOUT = 5;
    public static final int DEFAULT_EXPLICIT_WAIT_TIME_OUT = 15;
    public static final int DEFAULT_PAGE_LOAD_TIMEOUT = 90;
    private double implicitWaitTimeout = DEFAULT_IMPLICIT_WAIT_TIMEOUT;
    private int explicitWaitTimeout = DEFAULT_EXPLICIT_WAIT_TIME_OUT;
    private int pageLoadTimeout = DEFAULT_PAGE_LOAD_TIMEOUT;
    private String outputDirectory;
    private String browserVersion;
    private Platform platform;
    private String userAgentOverride;
    private String ntlmAuthTrustedUris;
    private String browserDownloadDir;
    private boolean addJSErrorCollectorExtension = false;
    private ArrayList<WebDriverEventListener> webDriverListeners;
    private boolean useFirefoxDefaultProfile = true;
    private int browserWindowWidth = -1;
    private int browserWindowHeight = -1;

    private String proxyHost;
    private String appName;
    private String appVersion;

    public ArrayList<WebDriverEventListener> getWebDriverListeners() {
        return webDriverListeners;
    }

    public void setWebDriverListeners(final ArrayList<WebDriverEventListener> webDriverListeners) {
        this.webDriverListeners = webDriverListeners;
    }

    public void setWebDriverListeners(final String listeners) {
        ArrayList<WebDriverEventListener> listenerList = new ArrayList<WebDriverEventListener>();
        String[] list = listeners.split(",");
        for (String aList : list) {

            WebDriverEventListener listener = null;
            try {
                if (!aList.equals("")) {
                    listener = (WebDriverEventListener) (Class.forName(aList)).newInstance();
                    listenerList.add(listener);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }

        this.webDriverListeners = listenerList;
    }

    public BrowserType getBrowser() {
        return browser;
    }

    public String getBrowserDownloadDir() {
        return browserDownloadDir;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public String getChromeBinPath() {
        return chromeBinPath;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public int getExplicitWaitTimeout() {
        if (explicitWaitTimeout < getImplicitWaitTimeout()) {
            return (int) getImplicitWaitTimeout();
        } else {
            return explicitWaitTimeout;
        }
    }

    public String getFirefoxBinPath() {
        return ffBinPath;
    }

    public String getFirefoxProfilePath() {
        if (ffProfilePath == null && getClass().getResource("/profiles/customProfileDirCUSTFF") != null) {

            try {
                return getClass().getResource("/profiles/customProfileDirCUSTFF").toURI().getPath();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else {
            return ffProfilePath;
        }
    }

    public String getHubUrl() {
        return hubUrl;
    }

    public String getIeDriverPath() {
        return ieDriverPath;
    }

    public double getImplicitWaitTimeout() {
        return implicitWaitTimeout;
    }

    public DriverMode getMode() {
        return mode;
    }

    public String getNtlmAuthTrustedUris() {
        return ntlmAuthTrustedUris;
    }

    public String getOperaProfilePath() {
        if (operaProfilePath == null && getClass().getResource("/profiles/operaProfile") != null) {

            try {
                return getClass().getResource("/profiles/operaProfile").toURI().getPath();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        return operaProfilePath;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public Platform getPlatform() {
        return platform;
    }

    public Proxy getProxy() {
        Proxy proxy = null;
        if (proxyHost != null) {
            proxy = new Proxy();
            proxy.setProxyType(ProxyType.MANUAL);
            proxy.setHttpProxy(proxyHost);
            proxy.setFtpProxy(proxyHost);
            proxy.setSslProxy(proxyHost);
        }

        return proxy;

    }

    public String getProxyHost() {
        return proxyHost;
    }

    public String getUserAgentOverride() {
        return this.userAgentOverride;
    }

    public int getWebSessionTimeout() {
        return webSessionTimeout;
    }

    public boolean isAddJSErrorCollectorExtension() {
        return addJSErrorCollectorExtension;
    }

    public boolean isUseFirefoxDefaultProfile() {
        return this.useFirefoxDefaultProfile;
    }

    public void setUseFirefoxDefaultProfile(final boolean useFirefoxDefaultProfile) {
        this.useFirefoxDefaultProfile = useFirefoxDefaultProfile;
    }

    public boolean isEnableJavascript() {
        return enableJavascript;
    }

    public boolean isSetAcceptUntrustedCertificates() {
        return setAcceptUntrustedCertificates;
    }

    public boolean isSetAssumeUntrustedCertificateIssuer() {
        return setAssumeUntrustedCertificateIssuer;
    }

    public void setAddJSErrorCollectorExtension(final boolean addJSErrorCollectorExtension) {
        this.addJSErrorCollectorExtension = addJSErrorCollectorExtension;
    }

    public void setBrowser(final BrowserType browser) {
        this.browser = browser;
    }

    public void setBrowserDownloadDir(final String browserDownloadDir) {
        this.browserDownloadDir = browserDownloadDir;
    }

    public void setBrowserVersion(final String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public void setChromeBinPath(final String chromeBinPath) {
        this.chromeBinPath = chromeBinPath;
    }

    public void setChromeDriverPath(final String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
    }

    public void setDriver(final WebDriver driver) {
        this.driver = driver;
    }

    public void setEnableJavascript(final boolean enableJavascript) {
        this.enableJavascript = enableJavascript;
    }

    public void setExplicitWaitTimeout(final int explicitWaitTimeout) {
        this.explicitWaitTimeout = explicitWaitTimeout;
    }

    public void setFfBinPath(final String ffBinPath) {
        this.ffBinPath = ffBinPath;
    }

    public void setFfProfilePath(final String ffProfilePath) {
        this.ffProfilePath = ffProfilePath;
    }

    public void setHubUrl(final String hubUrl) {
        this.hubUrl = hubUrl;
    }

    public void setIeDriverPath(final String ieDriverPath) {
        this.ieDriverPath = ieDriverPath;
    }

    public void setImplicitWaitTimeout(final double implicitWaitTimeout) {
        this.implicitWaitTimeout = implicitWaitTimeout;
    }

    public void setMode(final DriverMode mode) {
        this.mode = mode;
    }

    public void setNtlmAuthTrustedUris(final String ntlmAuthTrustedUris) {
        this.ntlmAuthTrustedUris = ntlmAuthTrustedUris;
    }

    public void setOperaProfilePath(final String operaProfilePath) {
        this.operaProfilePath = operaProfilePath;
    }

    public void setOutputDirectory(final String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public void setPageLoadTimeout(final int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public void setPlatform(final Platform platform) {
        this.platform = platform;
    }

    public void setProxyHost(final String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public void setSetAcceptUntrustedCertificates(final boolean setAcceptUntrustedCertificates) {
        this.setAcceptUntrustedCertificates = setAcceptUntrustedCertificates;
    }

    public void setSetAssumeUntrustedCertificateIssuer(final boolean setAssumeUntrustedCertificateIssuer) {
        this.setAssumeUntrustedCertificateIssuer = setAssumeUntrustedCertificateIssuer;
    }

    public void setUserAgentOverride(final String userAgentOverride) {
        this.userAgentOverride = userAgentOverride;
    }

    public void setWebSessionTimeout(final int webSessionTimeout) {
        this.webSessionTimeout = webSessionTimeout;
    }

    public void setAppName(final String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppVersion(final String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public int getBrowserWindowWidth() {
        return browserWindowWidth;
    }

    public void setBrowserWindowWidth(final int browserWindowWidth) {
        this.browserWindowWidth = browserWindowWidth;
    }

    public int getBrowserWindowHeight() {
        return browserWindowHeight;
    }

    public void setBrowserWindowHeight(final int browserWindowHeight) {
        this.browserWindowHeight = browserWindowHeight;
    }

}
