package pl.kubas.androidar.adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kubas.androidar.R;
import pl.kubas.androidar.buttons.ButtonAction;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> implements ButtonTurnOff {
    private List<ButtonAction> buttonList;

    public ButtonAdapter(List<ButtonAction> buttonList) {
        applyTurnOffInterface(buttonList);
    }

    @Override
    public ButtonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ButtonAction buttonAction = buttonList.get(position);
        holder.actionButton.setImageDrawable(buttonAction.getDrawable());
        holder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonAction.performAction(holder.actionButton);
            }
        });

    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }

    @Override
    public void turnOffEveryButton() {
        for (ButtonAction button : buttonList) {
            button.turnOffBehavior();
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.button_icon)
        FloatingActionButton actionButton;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    private void applyTurnOffInterface(List<ButtonAction> list) {
        for (ButtonAction button : list) {
            button.setTurnOffInterface(this);
        }
        this.buttonList = list;
    }
}