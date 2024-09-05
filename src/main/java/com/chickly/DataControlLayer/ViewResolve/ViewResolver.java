package com.chickly.DataControlLayer.ViewResolve;

import lombok.Getter;

@Getter
public class ViewResolver {
    private String view;
    private ResolveAction resolveAction;

    public ViewResolver() {
    }

    public ViewResolver(final String view) {
        this.view = view;
        resolveAction = ResolveAction.FORWARD;
    }

    public void setView(final String view) {
        this.view = view;
    }

    public void setResolveAction(final ResolveAction resolveAction) {
        this.resolveAction = resolveAction;
    }

    public void forward(final String view) {
        setView(view);
        resolveAction = ResolveAction.FORWARD;
    }

    public void redirect(final String view) {
        resolveAction = ResolveAction.REDIRECT;
    }
}
